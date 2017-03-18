(ns template.navigation
  (:require [secretary.core :as secretary :refer-macros [defroute]]
            [reagent.session :as session]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [template.nav-bar :refer [NavBar]]
            [template.login-form :refer [LoginForm]]
            [template.registration-form :refer [RegistrationForm]])
  (:import goog.History))


(defn page [comms {title :title
                   form :form}]
  [:div.container
   [NavBar comms]
   [:div.panel.panel-default
    [:div.panel-heading
     [:h2 title]]
    [:div.panel-body
     [form comms]]]])

(defn registration-page [comms]
  (page comms {:title "Registration"
               :form  RegistrationForm}))

(defn login-page [comms]
  (page comms {:title "Login"
               :form LoginForm}))

(def pages
  {:login login-page
   :registration registration-page})

(defn current-page [comms]
  (let [page-key (session/get :current-page)
        page-fn (pages page-key)]
    [:div [page-fn comms]]))

;; -------------------------
;; Routes
(secretary/set-config! :prefix "#")

(secretary/defroute "/registration" []
                    (session/put! :current-page :registration))

(secretary/defroute "/login" []
                    (session/put! :current-page :login))

(session/put! :current-page :login)

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