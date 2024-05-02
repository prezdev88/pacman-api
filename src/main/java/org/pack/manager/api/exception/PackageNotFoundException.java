package org.pack.manager.api.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PackageNotFoundException extends RuntimeException {
    public PackageNotFoundException(String message) {
        super(message);
    }
}
