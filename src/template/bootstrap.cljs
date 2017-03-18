(ns template.bootstrap
  (:require [reagent.core :as reagent :refer [atom]]
            [cljsjs.react-bootstrap]))

(def Button (reagent/adapt-react-class (aget js/ReactBootstrap "Button")))