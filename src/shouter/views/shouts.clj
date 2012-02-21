(ns shouter.views.shouts
  (:use [hiccup.core :only [html h]]
        [hiccup.page-helpers :only [doctype]]
        [hiccup.form-helpers :only [form-to label text-area submit-button]]
        [noir.core :only [defpage defpartial]]
        [noir.fetch.remotes :only [defremote]])
  (:require [shouter.views.common :as common]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [shouter.models.shout :as model]))

(defpartial shout-form []
  [:div {:id "shout-form" :class "sixteen columns alpha omega"}
   (form-to [:post "/"]
            (label "shout" "What do you want to SHOUT?") 
            (text-area "shout")
            (submit-button {:id "shout-button"} "SHOUT!"))])

(defpartial shout-container []
  [:div {:id "shouts" :class "sixteen columns alpha omega"}])

(defpage "/" []
  (common/layout "SHOUTER"
                 (shout-form)
                 [:div {:class "clear"}]
                 (shout-container)))

(defpage [:post "/"] {shout :shout}
  (when-not (str/blank? shout)
    (model/create shout))
  (ring/redirect "/"))

(defremote shouts-remote []
  (model/all))
