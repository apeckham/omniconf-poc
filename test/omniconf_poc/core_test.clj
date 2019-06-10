(ns omniconf-poc.core-test
  (:require [clojure.test :refer :all]
            [omniconf-poc.core :refer :all]
            [omniconf.core :as cfg]
            [omniconf-poc.commands :refer [connect disconnect]]))

(defn with-cfg [values f]
  (with-redefs [cfg/config-values (atom (sorted-map))]
    (cfg/populate-from-map values)
    (cfg/verify :silent true)
    (f)))

(deftest connect-test
  (with-cfg {:command :connect :hostname "asdf"}
    #(is (= "Connecting to asdf!\n" (with-out-str (connect))))))

(deftest disconnect-test
  (with-cfg {:command :disconnect :confirm false}
    #(is (= "Disconnecting! (confirm is false)\n" (with-out-str (disconnect))))))
