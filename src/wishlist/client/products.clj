(ns wishlist.client.products
  (:require [clojure.data.json :as json]
            [clojure.tools.logging :as log]
            [clj-http.client :as client]
            [clojure.core.cache.wrapped :as wrapped]
            [wishlist.cache.products :as cache]))

(defn get-products [page]
  (let [products (json/read-json
                  (:body
                   (client/get
                    (str "http://challenge-api.luizalabs.com/api/product/?page=" page))))]
    (cache/set-product page products)
    products))

(defn get-product-by-id [id]
  (log/info (str "Passei aqui ID: " id))
  (let [product (json/read-json
                 (:body
                  (client/get
                   (str "http://challenge-api.luizalabs.com/api/product/" id))))]
    (cache/set-product id product)
    product))
