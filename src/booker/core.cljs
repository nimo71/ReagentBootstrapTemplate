(ns booker.core
  (:require [reagent.core :as reagent :refer [atom]]
            [booker.bootstrap :refer [Button]]
            [booker.login-form :refer [LoginForm]]
            [booker.registration-form :refer [RegistrationForm]]))

(enable-console-print!)

(println "This text is printed from src/booker/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Booker"}))

(defn registration-page []
  [:div.container
   [:div.row
    [:h1 (:text @app-state)]]
   [:div.row
    [:div.col
      [RegistrationForm]]]])

(reagent/render-component [registration-page]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
