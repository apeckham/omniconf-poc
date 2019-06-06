(ns omniconf-poc.core
  (:gen-class)
  (:require [omniconf.core :as cfg]))

(cfg/define
  {:hostname {:description "where service is deployed"
              :type :string
              :required true}
   :port     {:description "HTTP port"
              :type :number
              :default 8080}})

(defn -main
  [command & args]
  (cfg/populate-from-cmd args)
  (cfg/populate-from-env)
  (cfg/verify)

  (println "Hello, World!"))
