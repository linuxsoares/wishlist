(ns wishlist.controller.products
  (:require [wishlist.client.products :as client]
            [wishlist.cache.products :as cache]
            [wishlist.db.products :as db-product]
            [wishlist.controller.customer :as customer]
            [wishlist.logic.customer :as logic-customer]))

(defn get-products [page]
  (if (cache/get-by-id page)
    (cache/get-by-id page)
    (client/get-products page)))

(defn get-product-by-id [id]
  (if (cache/get-by-id id)
    (cache/get-by-id id)
    (client/get-product-by-id id)))

(defn get-favorite-products-by-customer-id [customer-id]
  (db-product/get-favovite-products-by-customer-id customer-id))

(defn add-favorite [customer-id product-id]
  (db-product/add-product-favovite customer-id product-id)
  (let [customer-id (read-string customer-id)
        customer (customer/get-customer-by-id customer-id)
        products (db-product/get-favovite-products-by-customer-id customer-id)]
    (logic-customer/build-customer-favorite-products customer products)))

(defn remove-favorite [customer-id product-id]
  (db-product/remove-product-favovite customer-id product-id)
  (let [customer-id (read-string customer-id)
        customer (customer/get-customer-by-id customer-id)
        products (db-product/get-favovite-products-by-customer-id customer-id)]
    (logic-customer/build-customer-favorite-products customer products)))
