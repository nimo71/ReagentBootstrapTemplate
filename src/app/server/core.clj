(ns app.server.core
  [:require [compojure.core :as compojure :refer [defroutes GET POST]]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [chord.http-kit :refer [wrap-websocket-handler with-channel]]
            [org.httpkit.server :refer [run-server]]
            [clojure.core.async :refer [<! >! put! close! go go-loop]]])

(defonce server (atom nil))

(defn ws-handler [{:keys [ws-channel] :as req}]
  (go-loop []
    (let [{:keys [message]} (<! ws-channel)]
      (println "Message received:" message)
      (>! ws-channel {:echo message})
      (when message (recur)))))

(defn start-server [port]
  (println "Server started on port " port "...")
  (reset! server (run-server (-> #'ws-handler (wrap-websocket-handler)) {:port port})))

(defn stop-server []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil))
  (println "Stopping server."))