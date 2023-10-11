(ns liberator-hal-events-resource.events
  (:require
    [halboy.resource :as hal]))

(defmulti event->resource (fn [_ _ _ e] (:type e)))

(defmethod event->resource :test-event-1
  [dependencies request routes event]
  (->
    (hal/new-resource "blabla")
    (hal/add-properties
      {:event event})))

(defmethod event->resource :test-event-2
  [dependencies request routes event]
  (->
    (hal/new-resource "blaBla2")
    (hal/add-properties
      {:event event})))
