# Secret Santinator

A small application to match Secret Santa gifters to giftees and secretly inform the gifters by email

## What is Secret Santa?

In [Secret Santa](https://en.wikipedia.org/wiki/Secret_Santa) every participant secretly gets assigned another person. They will then find a small gift for that person. That person must then find out from whom they got the gift.

In my family, we started playing this game every year for Christmas, as it allows to keep the magic of gift-giving alive while reducing the chaos of "everyone needing to find a gift for everyone". To make this possible, I wrote this little application.

## What does this thing do?

The key bit of Secret Santa is that the assigning of gifter to giftee is done in secret. This program is meant to act as a neutral party which produces this assignment and then informs the gifters by email.

## How do I configure this thing?

You need to write the 2 config files:

The `email_config.yml` file includes all the settings for the email sending such as mail server credentials and the content of the email. You can copy the `email_config.sample.yml` file as a template. Simply copy it, rename it to `email_config.yml` and fill in all the details.

The `people.yml` file includes a list of all participants. Each person consists of a `name` and an `email`, and optionally an `id`, which may be used in the email subject or body. You can copy the `people.sample.yml` file as a template.

Once the config files are set up, you can run 

## To Do

- Allow generating multi-cycle mappings
- Store mapping into file
- Allow providing list of forbidden / discouraged mappings
