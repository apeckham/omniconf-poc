(ns omniconf-poc.core-test
  (:require [clojure.test :refer :all]
            [omniconf-poc.core :refer :all]
            [omniconf.core :as cfg]
            [omniconf-poc.commands :refer [connect disconnect]]))

(defn setup-cfg [values]
  (cfg/populate-from-map values)
  (cfg/verify :silent true))

(deftest connect-test
  (with-redefs [cfg/config-values (atom (sorted-map))]
    (setup-cfg {:command :connect :hostname "asdf"})
    (is (= "Connecting to asdf!\n" (with-out-str (connect))))))

(deftest disconnect-test
  (with-redefs [cfg/config-values (atom (sorted-map))]
    (setup-cfg {:command :disconnect :confirm true})
    (cfg/set :confirm false)
    (is (= "Disconnecting! (confirm is false)\n" (with-out-str (disconnect))))))
