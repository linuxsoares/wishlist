(ns wishlist.schema.customer
  (:require [schema.core :as s]))

(s/defschema Customer
  {:email #"^[\w+.]+@\w+\.\w{2,}(?:\.\w{2})?$"
   :name s/Str})

(s/defschema CustomerResponse
  {:id s/Int
   :email #"^[\w+.]+@\w+\.\w{2,}(?:\.\w{2})?$"
   :name s/Str
   (s/optional-key :favorite-products) [s/Str]})

(s/defschema UpdateCustomer
  {:email #"^[\w+.]+@\w+\.\w{2,}(?:\.\w{2})?$"})
