(ns app.client.core
  (:require-macros [cljs.core.async.macros :refer [go-loop go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [app.client.navigation :as nav]
            [reagent.session :as session]
            [cljs.core.async :refer [chan <! >! put! close!]]
            [chord.client :refer [ws-ch]]))

(enable-console-print!)

(defonce app-state (atom {:comms (chan)
                          :text "Template"}))

(nav/hook-browser-navigation!)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

;; -------------------------
;; Application event loop

(set! (.-onload js/window)
  (fn []
    (go
      (let [{:keys [ws-channel error]} (<! (ws-ch "ws://localhost:8080/ws"))]

        (if error

          (reagent/render-component
            [:div "Couldn't connect to websocket: " (pr-str error)]
            js/document.body)

          (reagent/render-component [nav/current-page (:comms @app-state)]
            (. js/document (getElementById "app"))))

        (go-loop []
          (println "receive loop")
          (let [{:keys [message error]} (<! ws-channel)]
            (if-not error
              (println "received: " (pr-str message))
              (println "Error: " error))
            (when message (recur))))

        (go-loop []
          (println "send loop")
          (when-let [msg (<! (:comms @app-state))]
            (>! ws-channel msg)
            (recur)))))))
