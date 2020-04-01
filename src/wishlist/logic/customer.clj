(ns wishlist.)
(ns wishlist.logic.customer
  (:require [clojure.tools.logging :as log]))

(defn build-customer-favorite-products [customer products]
  (log/info (str "Build customer " customer " with products " products))
  {:name (:name customer)
   :email (:email customer)
   :id (:id customer)
   :favorite-products (into [] products)})

