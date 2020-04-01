(ns wishlist.controller.customer
  (:require [wishlist.db.customer :as db]
            [wishlist.db.products :as db-products]
            [clojure.tools.logging :as log]
            [wishlist.logic.customer :as customer-logic]))

(defn create-customer [customer]
  (db/create-customer (assoc customer :favorite-products [])))

(defn get-customer-by-id [customer-id]
  (let [customer (db/get-customer-by-id customer-id)
        products (db-products/get-favovite-products-by-customer-id customer-id)]
    (customer-logic/build-customer-favorite-products customer products)))

(defn ^:private format-customer [customer]
  (let [customer-id (nth customer 0)
        products (db-products/get-favovite-products-by-customer-id customer-id)
        old-customer (nth customer 1)]
    (assoc old-customer :products products)))

(defn all-customers []
  (let [customers (db/all-customers)]
    (map format-customer customers)))

(defn update-customer [customer-id customer]
  (let [update-customer (db/update-customer customer-id customer)
        products (db-products/get-favovite-products-by-customer-id customer-id)]
    (log/info (str "Update Customer " update-customer))
    (customer-logic/build-customer-favorite-products (get update-customer customer-id) products)))

(defn remove-customer-by-id [customer-id]
  (db/remove-customer-by-id customer-id))
