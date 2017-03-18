(ns template.core
  (:require-macros [cljs.core.async.macros :refer [go-loop]])
  (:require [reagent.core :as reagent :refer [atom]]
            [template.navigation :as nav]
            [reagent.session :as session]
            [cljs.core.async :refer [chan <!]]))

(enable-console-print!)

(defonce app-state (atom {:comms (chan)
                          :text "Template"}))

(nav/hook-browser-navigation!)

(reagent/render-component [nav/current-page (:comms @app-state)]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

;; -------------------------
;; Application event loop
(go-loop [ev (<! (:comms @app-state))]
  (println "Event: " ev)
  (recur (<! (:comms @app-state))))