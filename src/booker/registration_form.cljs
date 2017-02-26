(ns booker.registration-form
  (:require [cljs.core.async :refer [put!]]))

(defn register [comms data]
  (put! comms {:register data}))

(defn change-data [data field event]
  (swap! data assoc field (-> event .-target .-value)))

(defn RegistrationForm [comms]
  (let [data (atom {:email "" :confirm-email "" :password "" :confirm-password ""})]
    [:form
     [:div.form-group
      [:label {:for "email"} "Email"]
      [:input.form-control {:type "email" :id "email" :arial-described-by "emailHelp" :placeholder "Enter email"
                            :onChange #(change-data data :email %)}]
      [:small.form-text.text-muted {:id "emailHelp"} "We'll never share your email with anyone else"]]
     [:div.form-group
      [:label {:for "confirmEmail"} "Confirm email"]
      [:input.form-control {:type "email" :id "confirmEmail" :placeholder "Confirm email"
                            :onChange #(change-data data :confirm-email %)}]]
     [:div.form-group
      [:label {:for "password"} "Password"]
      [:input.form-control {:type "password" :id "password" :placeholder "Password"
                            :onChange #(change-data data :password %)}]]
     [:div.form-group
      [:label {:for "confirmPassword"} "Confirm password"]
      [:input.form-control {:type "password" :id "confirmPassword" :placeholder "Confirm password"
                            :onChange #(change-data data :confirm-password %)}]]

     [:button.btn.btn-primary {:type "button" :on-click #(register comms @data)} "Register"]]))