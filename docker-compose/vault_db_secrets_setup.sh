#!/bin/bash

# Add database secrets engine
vault secrets enable database

# Configure database connection
# allowed_roles : Defines which roles can request credentials for this database.
vault write database/config/my-postgresql-database \
    plugin_name=postgresql-database-plugin \
    allowed_roles="dynamic-role" \
    connection_url="postgresql://{{username}}:{{password}}@postgres:5432/spring-cloud-vault-demo?sslmode=disable" \
    username="postgres" \
    password="Simform@123"

# Add dynamic role with expanded permissions
# creation_statements : Defines SQL statements to create a user and grant permissions. Here, Vault generates credentials and assigns database access based on the role.
# TTL settings: Set the lifespan of these credentials. default_ttl defines a shorter lifespan, and max_ttl defines the maximum allowed lifespan.
vault write database/roles/dynamic-role \
    db_name=my-postgresql-database \
    creation_statements="CREATE ROLE \"{{name}}\" WITH LOGIN PASSWORD '{{password}}' VALID UNTIL '{{expiration}}'; \
                        GRANT CONNECT ON DATABASE \"spring-cloud-vault-demo\" TO \"{{name}}\"; \
                        GRANT USAGE ON SCHEMA public TO \"{{name}}\"; \
                        GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO \"{{name}}\"; \
                        GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO \"{{name}}\";" \
    default_ttl="1h" \
    max_ttl="24h"

