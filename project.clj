(defproject shouter "1.0.0-SNAPSHOT"
  :description "SHOUT from the webtops"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.clojure/java.jdbc "0.1.1"]
                 [postgresql/postgresql "8.4-702.jdbc4"]
                 [noir "1.3.0-alpha10"]
                 ;; cljs stuff
                 [jayq "0.1.0-SNAPSHOT"]
                 [crate "0.1.0-SNAPSHOT"]
                 [fetch "0.1.0-SNAPSHOT"]
                 ]
  :plugins [[lein-cljsbuild "0.1.2"]]
  :hooks [leiningen.cljsbuild]
  :cljsbuild {:builds
              [{:source-path "src",
                :compiler
                {:output-dir "resources/public/cljs/",
                 :output-to "resources/public/cljs/bootstrap.js",
                 :optimizations :simple,
                 :pretty-print true}}]}
  :main shouter.main)