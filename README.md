# liberator-hal-events-resource
[![Clojars Project](https://img.shields.io/clojars/v/ai.mypulse/liberator-hal-events-resource.svg)](https://clojars.org/ai.mypulse/liberator-hal-events-resource)

A Clojure library for liberator to load and transform events to HAL resources.

This library uses [Halboy](https://github.com/jimmythompson/halboy) for creating and rendering HAL resources.

```clj
[ai.mypulse/liberator-hal-events-resource "0.1.0"]
```

## Usage

You'll need to provide 2 function
- Event loader: how to load your events from their store. (for example your database)
- Event transformer (how to map those events into HAL resources)

``` clojure
(defn events-resource-handler-for [{:keys [events-store] :as dependencies}]
  (events-resource dependencies default-page-size
    (partial db-events-loader events-store) #event-loader-function
    event-mapping/event->resource)) #event-transformer function
```
## License

Copyright MyPulse &copy; 2022

Distributed under the MIT license.

## Secrets

#### Managing CircleCI keys

To encrypt a GPG key for use by CircleCI:

```bash
openssl aes-256-cbc \
  -e \
  -md sha1 \
  -in ./config/secrets/ci/gpg.private \
  -out ./.circleci/gpg.private.enc \
  -k "<passphrase>"
```

To check decryption is working correctly:

```bash
openssl aes-256-cbc \
  -d \
  -md sha1 \
  -in ./.circleci/gpg.private.enc \
  -k "<passphrase>"
```
