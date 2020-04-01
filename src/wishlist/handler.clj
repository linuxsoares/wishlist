(ns wishlist.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]
            [clojure.tools.logging :as log]
            [wishlist.controller.products :as products]
            [wishlist.controller.customer :as controller_customer]
            [wishlist.schema.customer :as customer])
  (:gen-class))

(def app
  (api
   {:swagger
    {:ui "/"
     :spec "/swagger.json"
     :data {:info {:title "WishList"
                   :description "Challenge WithList"}
            :tags [{:name "api", :description "APIs Wishlist"}]}}}

   (context "/api" []
     :tags ["api"]

     (GET "/products" []
       :summary "Get products"
       :query-params [page :- String]
       (ok (products/get-products page)))

     (GET "/products/:id" [id]
       :summary "Get product by ID"
       (try
         (ok (products/get-product-by-id id))
         (catch Exception e
           (log/error (str "Get Product ID: " id "Error: " e))
           {:status 404
            :body {:message (str "Product ID:" id "Not Found!")}})))

     (GET "/customer/:customer-id" [customer-id]
       :summary "Get customer"
       :return customer/CustomerResponse
       (ok
        (controller_customer/get-customer-by-id (read-string customer-id))))

     (GET "/customer" []
       :summary "Get customers"
       (ok
        (controller_customer/all-customers)))

     (POST "/customer" [request]
       :return customer/CustomerResponse
       :body [customer customer/Customer]
       (ok
        (controller_customer/create-customer customer)))

     (PUT "/customer/:customer-id" [customer-id]
       :body [customer customer/UpdateCustomer]
       :return customer/CustomerResponse
       (ok (controller_customer/update-customer (read-string customer-id) customer)))

     (DELETE "/customer/:customer-id" [customer-id]
       (controller_customer/remove-customer-by-id (read-string customer-id))
       {:status 202})

     (POST "/customer/:customer-id/product-favorite/:product-id" [customer-id product-id]
       :return customer/CustomerResponse
       (ok (products/add-favorite customer-id product-id)))

     (DELETE "/customer/:customer-id/product-favorite/:product-id" [customer-id product-id]
       :return customer/CustomerResponse
       (ok (products/remove-favorite customer-id product-id))))))
