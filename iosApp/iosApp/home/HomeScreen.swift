import SwiftUI
import shared

struct HomeScreen: View {
	
    @StateObject var viewModel = HomeViewModel()
    
    let gridColumns: [GridItem] = Array(repeating: GridItem(.flexible(),spacing: 16), count: 2)
    
	var body: some View {
        NavigationStack{
            
            ScrollView{
                LazyVGrid(columns:gridColumns,spacing: 16){
                    ForEach(viewModel.movies,id:\.id){movie in
                        NavigationLink(value: movie){
                            MovieGridItem(movie: movie)
                        }.buttonStyle(PlainButtonStyle())
                    }
                }.padding(.horizontal,12)
                    .navigationDestination(for: Movie.self){movie in
                        DetailScreen(movie: movie)
                    }
            
            }.navigationTitle("Movies")
            
        }.task {
            await viewModel.loadMovies()
        }
        
	}
    
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
