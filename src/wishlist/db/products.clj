(ns wishlist.db.products)

(defonce favovite (atom {}))

(defn add-product-favovite [customer-id product-id]
  (let [id (inc (count @favovite))
        customer-id (keyword (str customer-id))]
    (swap! favovite conj {customer-id (conj (customer-id @favovite) product-id)})))

(defn remove-product-favovite [customer-id product-id]
  (let [customer-id (keyword (str customer-id))
        remove-product (remove #{product-id} (customer-id @favovite))]
    (swap! favovite conj {(keyword customer-id) remove-product})))

(defn get-favovite-products-by-customer-id [customer-id]
  (let [customer-id (keyword (str customer-id))]
    (customer-id @favovite)))
