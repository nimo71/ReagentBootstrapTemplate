(ns booker.core
  (:require-macros [cljs.core.async.macros :refer [go-loop]])
  (:require [reagent.core :as reagent :refer [atom]]
            [booker.bootstrap :refer [Button]]
            [booker.login-form :refer [LoginForm]]
            [booker.registration-form :refer [RegistrationForm]]
            [reagent.session :as session]
            [secretary.core :as secretary :refer-macros [defroute]]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [cljs.core.async :refer [chan put! <!]])
  (:import goog.History))

(enable-console-print!)

(defonce app-state (atom {:comms (chan)
                          :text "Booker"}))

(defn registration-page []
  [:div.container
   [:div.row
    [:h1 (:text @app-state)]]
   [:div.row
    [:div.col
      [RegistrationForm (:comms @app-state)]]]])

(defn login-page []
  [:div.container
   [:div.row
    [:h1 (:text @app-state)]]
   [:div.row
    [:div.col
     [LoginForm (:comms @app-state)]]]])

(defn current-page []
  [:div [(session/get :current-page)]])

;; -------------------------
;; Routes
(secretary/set-config! :prefix "#")

(secretary/defroute "/registration" []
  (session/put! :current-page #'registration-page))

(secretary/defroute "/login" []
  (session/put! :current-page #'login-page))

;; -------------------------
;; History
;; must be called after routes have been defined
(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
      EventType/NAVIGATE
      (fn [event]
        (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(hook-browser-navigation!)
(session/put! :current-page #'registration-page)
(reagent/render-component [current-page] (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

;; -------------------------
;; Application event loop
(go-loop [ev (<! (:comms @app-state))]
         (println "Event: " ev)
         (recur (<! (:comms @app-state))))