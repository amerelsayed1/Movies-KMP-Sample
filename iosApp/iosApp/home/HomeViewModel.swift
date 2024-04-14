//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Amer Elsayed on 14/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension HomeScreen{
    @MainActor class HomeViewModel: ObservableObject{
        
        private let getMoviesUseCase = GetMoviesUseCase.init()
        
        @Published private(set) var movies:[Movie] = []
        @Published private(set) var isLoading:Bool = false
        
        private var currentPage = 1
        private(set) var loadFinished: Bool = false
        
        func loadMovies() async{
            if isLoading {
                return
            }
            
            do {
                let movies = try await getMoviesUseCase.invoke(page: Int32(currentPage))
                isLoading = false
                loadFinished = movies.isEmpty
                
                self.movies = self.movies + movies
            }catch{
                isLoading = false
                loadFinished = true
                
                print(error.localizedDescription)
            }
        }
    }
}
