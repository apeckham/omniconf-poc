(ns omniconf-poc.config
  (:require [omniconf.core :as cfg]))

(defn define-cfg [command]
  (cfg/define
    (case command
      :connect
      {:hostname {:description "where service is deployed"
                  :type :string
                  :required true}
       :port     {:description "HTTP port"
                  :type :number
                  :default 8080}}

      :disconnect
      {:confirm {:type :boolean
                 :required true}})))
