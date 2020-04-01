(defproject wishlist "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [metosin/compojure-api "1.1.13"]
                 [org.clojure/tools.logging "1.0.0"]
                 [clj-http "3.10.0"]
                 [org.clojure/data.json "1.0.0"]
                 [org.clojure/core.cache "0.8.2"]]
  :plugins [[lein-ring "0.12.5"]
            [lein-cljfmt "0.6.7"]]
  :ring {:handler wishlist.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}}
  :uberjar-name "wishlist.jar")
