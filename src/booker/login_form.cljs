(ns booker.login-form)

(defn login [data]
  (println "login: " data))

(defn change-data [data field event]
  (swap! data assoc field (-> event .-target .-value)))

(defn LoginForm []
  (let [data (atom {:email "" :password ""})]
    [:form
     [:div.form-group
      [:label {:for "email"} "Email"]
      [:input.form-control {:type "email" :id "email" :arial-described-by "emailHelp" :placeholder "Enter email"
                            :onChange #(change-data data :email %)}]]
     [:div.form-group
      [:label {:for "password"} "Password"]
      [:input.form-control {:type "password" :id "password" :placeholder "Password"
                            :onChange #(change-data data :password %)}]]
     [:button.btn.btn-primary {:type "button" :on-click #(login @data)} "Login"]]))