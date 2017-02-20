(ns booker.login-form)

(defn login []
  (println "login"))

(defn LoginForm []
  [:form
   [:div.form-group
    [:label {:for "email"} "Email"]
    [:input.form-control {:type "email" :id "email" :arial-described-by "emailHelp" :placeholder "Enter email"}]]
   [:div.form-group
    [:label {:for "password"} "Password"]
    [:input.form-control {:type "password" :id "password" :placeholder "Password"}]]
   [:button.btn.btn-primary {:type "button" :on-click login} "Login"]])