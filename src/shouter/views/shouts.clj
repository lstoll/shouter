(ns shouter.views.shouts
  (:use [hiccup.core :only [html h]]
        [hiccup.page-helpers :only [doctype]]
        [hiccup.form-helpers :only [form-to label text-area submit-button]]
        [noir.core :only [defpage defpartial]])
  (:require [shouter.views.common :as common]
            [shouter.models.shout :as model]))

(defpartial shout-form []
  [:div {:id "shout-form" :class "sixteen columns alpha omega"}
   (form-to [:post "/"]
            (label "shout" "What do you want to SHOUT?") 
            (text-area "shout")
            (submit-button "SHOUT!"))])


(defpartial shout [shout]
  [:h2 {:class "shout"} (h (:body shout))])

(defpartial display-shouts [shouts]
  [:div {:id "shouts sixteen columns alpha omega"}
   (map
    shout
    shouts)])

(defpage "/" []
  (common/layout "SHOUTER"
                 (shout-form)
                 [:div {:class "clear"}]
                 (display-shouts (model/all))))
