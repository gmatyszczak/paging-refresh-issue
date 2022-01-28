# Paging Refresh Issue
Simple app to demonstrate issue with Android Jetpack Paging library in version 3.1.0, with load states when calling refresh() on PagingDataAdapter.

## Problem
If we set addLoadStateListener() on PagingDataAdpater and call refresh(), then in callback as a first LoadState we will get always NotLoading, instead of Loading. On version 3.0.1 it was working as desired.
