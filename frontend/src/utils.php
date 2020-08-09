<?php

function my_start_session() {
    if (php_sapi_name() !== 'cli') {
        if (version_compare(phpversion(), '5.4.0', '>=')) {
            if (session_status() === PHP_SESSION_ACTIVE) {
                return TRUE;
            } else {
                session_start();
            }
        }
    } else {
        if (session_id() === '') {
            session_start();
        } else {
            return TRUE;
        }
    }
    return FALSE;
}