#!/bin/bash

# Add database secrets engine
vault secrets enable database

# Configure database connection
vault write database/config/my-postgresql-database \
    plugin_name=postgresql-database-plugin \
    allowed_roles="dynamic-role" \
    connection_url="postgresql://{{username}}:{{password}}@postgres:5432/spring-cloud-vault-demo?sslmode=disable" \
    username="postgres" \
    password="Simform@123"

# Add dynamic role with expanded permissions
vault write database/roles/dynamic-role \
    db_name=my-postgresql-database \
    creation_statements="CREATE ROLE \"{{name}}\" WITH LOGIN PASSWORD '{{password}}' VALID UNTIL '{{expiration}}'; \
                        GRANT CONNECT ON DATABASE \"spring-cloud-vault-demo\" TO \"{{name}}\"; \
                        GRANT USAGE, CREATE ON SCHEMA public TO \"{{name}}\"; \
                        GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO \"{{name}}\"; \
                        GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO \"{{name}}\";" \
    default_ttl="1h" \
    max_ttl="24h"

