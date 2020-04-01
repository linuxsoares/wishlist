(ns wishlist.cache.products
  (:require [clojure.core.cache.wrapped :as wrapped]))

(def products-cache)

(def products-cache
  (wrapped/ttl-cache-factory {} :ttl 60000))

(defn get-by-id [id]
  (get @products-cache id))

(defn set-product [id data]
  (wrapped/through-cache products-cache id (constantly data)))
