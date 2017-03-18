(ns app.system
  (:require [com.stuartsierra.component :as component]
            [app.components :as app-components]
            [app.figwheel :as figwheel]))

(defn system-map [config]
  (component/system-map
    :server (app-components/map->Server (:web-server config))
    :figwheel (figwheel/system)))

(defn dependency-map []
  ; http://stackoverflow.com/questions/29070883/how-to-use-stuart-sierras-component-library-in-clojure
  {})

(defn create-system [& [config]]
  (component/system-using
    (system-map (or config {}))
    (dependency-map)))
