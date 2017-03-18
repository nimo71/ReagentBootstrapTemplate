(ns template.login-form
  (:require [cljs.core.async :refer [put!]]))

(defn login [comms data]
  (put! comms {:login data}))

(defn change-data [data field event]
  (swap! data assoc field (-> event .-target .-value)))

(defn LoginForm [comms]
  (let [data (atom {:email "" :password ""})]
    [:form
     [:div.form-group
      [:label {:for "email"} "Email"]
      [:input.form-control {:type     "email" :id "email" :arial-described-by "emailHelp" :placeholder "Enter email"
                            :onChange #(change-data data :email %)}]]
     [:div.form-group
      [:label {:for "password"} "Password"]
      [:input.form-control {:type     "password" :id "password" :placeholder "Password"
                            :onChange #(change-data data :password %)}]]
     [:button.btn.btn-primary.pull-right {:type "button" :on-click #(login comms @data)} "Login"]]))