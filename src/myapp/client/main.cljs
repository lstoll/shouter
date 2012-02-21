(ns shouter.client.main
  (:require [crate.core :as crate]
            [fetch.remotes :as remotes])
  (:require-macros [fetch.macros :as fm])
  (:use [jayq.core :only [$ append delegate data]])
  (:use-macros [crate.macros :only [defpartial]]))

(def $shouts ($ :#shouts))
(def $shout-form ($ :#shout-form))
(def $container ($ :#shouts))

(defpartial shout [shout]
  [:h2 {:class "shout"} (:body shout)])

(defn populate [container shouts]
  (doseq [b shouts]
    (append container (shout b))))

(delegate $shout-form :#shout-button :click
          (fn [e]
            (.preventDefault e)
            (js/alert "clicked!")))

(fm/letrem [shouts (shouts-remote)]
        (populate $container shouts))