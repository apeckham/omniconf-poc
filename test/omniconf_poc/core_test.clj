(ns omniconf-poc.core-test
  (:require [clojure.test :refer :all]
            [omniconf-poc.core :refer :all]
            [omniconf.core :as cfg]
            [omniconf-poc.config :refer [define-cfg]]
            [omniconf-poc.commands :refer [connect]]))

(deftest connect-test
  (define-cfg :connect)
  (cfg/populate-from-map {:hostname "asdf"})
  (is (= "Connecting to asdf!\n" (with-out-str (connect)))))
