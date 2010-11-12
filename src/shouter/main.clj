(ns shouter.main
  (:use [ring.adapter.jetty :only [run-jetty]]
	[ring.middleware.params :only (wrap-params)]        
	[ring.middleware.keyword-params :only [wrap-keyword-params]]
        [compojure.core :only [defroutes]])
  (:require [compojure.route :as route]
            [shouter.controllers.shouts]))

(defroutes routes
  shouter.controllers.shouts/routes
  (route/files "/")
  (route/not-found "Not Found"))

(def application (-> routes
                     wrap-keyword-params
                     wrap-params))

(defn start
  []
  (run-jetty (var application) {:port 8080
                                :join? false}))
