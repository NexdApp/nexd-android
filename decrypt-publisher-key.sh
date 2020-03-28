#!/bin/sh

# --batch to prevent interactive command --yes to assume "yes" for questions
gpg --quiet --batch --yes --decrypt --passphrase="$PUBLISHER_KEY_PASSPHRASE" \
--output publisher-key.json publisher-key.json.gpg