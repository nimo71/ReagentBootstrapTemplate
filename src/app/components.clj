(ns app.components
  (:require [com.stuartsierra.component :as component]
            [app.server.core :refer [start-server stop-server]]))

(defrecord Server [port]
  component/Lifecycle
  (start [c]
    (assoc c :server (start-server port)))

  (stop [c]
    (when-let [server (:server c)]
      (stop-server))
    (dissoc c :server)))