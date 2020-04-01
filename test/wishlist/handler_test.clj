(ns wishlist.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [wishlist.handler :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 302)))))
