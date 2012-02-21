(ns shouter.views.common
  (:use [noir.core :only [defpartial]]
        [noir.statuses :only [set-page!]]
        [hiccup.page-helpers :only [include-css include-js html5]]))
        
(defpartial layout [title & body]
  (html5
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1, maximum-scale=1"}]
    [:title title]
    (include-css "/stylesheets/base.css"
                 "/stylesheets/skeleton.css"
                 "/stylesheets/screen.css")
    (include-css "http://fonts.googleapis.com/css?family=Sigmar+One&v1")
    (include-js "https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js")]
   [:body
    [:div {:id "header"}
     [:h1 {:class "container"} "SHOUTER"]]
    [:div {:id "content" :class "container"} body]
    (include-js "/cljs/bootstrap.js")]))

; 404 handler
(noir.statuses/set-page! 404
  (layout "Page Not Found"
          [:div {:id "four-oh-four"}
           [:h1 "The page you requested could not be found"]]))
