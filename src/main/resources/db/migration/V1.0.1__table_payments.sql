CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    subscription_id BIGINT NOT NULL,
    transaction_id VARCHAR(255),
    amount NUMERIC,
    status VARCHAR(255),
    payment_method VARCHAR(255),
    error_message TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_payment_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_payment_user_subscription FOREIGN KEY (subscription_id) REFERENCES user_subscriptions(id)
);