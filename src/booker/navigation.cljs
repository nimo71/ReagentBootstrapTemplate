(ns booker.navigation
  (:require [secretary.core :as secretary :refer-macros [defroute]]
            [reagent.session :as session]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [booker.login-form :refer [LoginForm]]
            [booker.registration-form :refer [RegistrationForm]])
  (:import goog.History))

(defn registration-page [comms]
  [:div.container
   [:div.row
    [:h1 "Registration"]]
   [:div.row
    [:div.col
     [RegistrationForm comms]]]])

(defn login-page [comms]
  [:div.container
   [:div.row
    [:h1 "Login"]]
   [:div.row
    [:div.col
     [LoginForm comms]]]])

(defn current-page [comms]
  [:div [(session/get :current-page) comms]])

;; -------------------------
;; Routes
(secretary/set-config! :prefix "#")

(secretary/defroute "/registration" []
                    (session/put! :current-page #'registration-page))

(secretary/defroute "/login" []
                    (session/put! :current-page #'login-page))

(session/put! :current-page #'registration-page)

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