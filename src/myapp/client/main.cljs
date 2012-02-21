(ns shouter.client.main
  (:require [crate.core :as crate]
            [fetch.remotes :as remotes])
  (:require-macros [fetch.macros :as fm])
  (:use [jayq.core :only [$ append delegate data val prepend]])
  (:use-macros [crate.macros :only [defpartial]]))

(def $shouts ($ :#shouts))
(def $shout-form ($ :#shout-form))
(def $shout-input ($ :#shout-input))
(def $container ($ :#shouts))

(defpartial shout [shout]
  [:h2 {:class "shout"} (:body shout)])

(defn populate [container shouts]
  (doseq [b shouts]
    (append $container (shout b))))

(delegate $shout-form :#shout-button :click
          (fn [e]
            (.preventDefault e)
            (let [newshout (val $shout-input)]
              (if (not= newshout "")
                (do
                  (fm/remote (create-shout-remote newshout))
                  (prepend $container (shout {:body newshout}))
                  (val $shout-input ""))
                (js/alert "You can't shout nothing!")))))

(fm/letrem [shouts (shouts-remote)]
        (populate $container shouts))