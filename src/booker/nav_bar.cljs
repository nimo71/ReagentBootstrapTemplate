(ns booker.nav-bar
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent.session :as session]))

(defn NavBar [comms]
  (let [current-page (session/get :current-page)]
    [:nav.navbar.navbar-inverse.navbar-fixed-top
     [:div.container
      [:div.navbar-header
       [:a.navbar-brand {:href "#"} "Booker"]]
      [:ul.nav.navbar-nav

       [:li {:class (when (= :login current-page) "active")}
          [:a {:href "/#/login"} "Login"]]

       [:li {:class (when (= :registration current-page) "active")}
          [:a {:href "/#/registration"} "Registration"]]]]]))
