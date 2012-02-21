(ns shouter.main
  (:require [noir.server :as server])
  (:use [noir.fetch.remotes]))

(server/load-views "src/shouter/views/")

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/add-middleware wrap-remotes)
    (server/start port {:mode mode
                        :ns 'pinotnoir})))
