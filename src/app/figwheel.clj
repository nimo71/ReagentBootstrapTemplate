(ns app.figwheel
  [:require [figwheel-sidecar.system :as sys]
            [clojure.pprint :refer [pprint]]])

(defn system []
  (sys/figwheel-system (sys/fetch-config)))
