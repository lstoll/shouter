(defproject shouter "1.0.0-SNAPSHOT"
  :description "SHOUT from the webtops"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.clojure/java.jdbc "0.1.1"]
                 [postgresql/postgresql "8.4-702.jdbc4"]
                 [noir "1.2.1"]]
  :main shouter.main)