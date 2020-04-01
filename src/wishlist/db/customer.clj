(ns wishlist.db.customer)

(defonce customers (atom {}))

(defn create-customer [customer]
  (let [id (inc (count @customers))]
    (get (swap! customers assoc id (assoc customer :id id)) id)))

(defn get-customer-by-id [customer-id]
  (get @customers customer-id))

(defn remove-customer-by-id [customer-id]
  (swap! customers dissoc customer-id))

(defn update-customer [id customer]
  (swap! customers update id merge customer))

(defn all-customers []
  @customers)
