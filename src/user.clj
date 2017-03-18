(ns user
  (:require [app.system :as system]
            [com.stuartsierra.component :as component]
            [clojure.tools.namespace.repl :refer [refresh refresh-all]]
            [dev-system :refer [the-system]]))

(def system-config
  {:web-server {:port 8080}})

(defn init []
  (alter-var-root #'the-system
    (constantly (system/create-system system-config))))

(defn start []
  (alter-var-root #'the-system component/start))

(defn stop []
  (alter-var-root #'the-system
    #(when % (component/stop %))))

(defn go []
  (init)
  (start))

(defn reset []
  (stop)
  (refresh :after 'user/go))
