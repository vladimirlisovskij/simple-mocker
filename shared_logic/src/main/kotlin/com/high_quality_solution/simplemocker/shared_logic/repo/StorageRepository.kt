package com.high_quality_solution.simplemocker.shared_logic.repo

import android.net.Uri

interface StorageRepository {
    fun getReadUriForFileName(clientPackage: String, fileName: String): Uri
}