//
//  DetailScreen.swift
//  iosApp
//
//  Created by Amer Elsayed on 14/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DetailScreen: View {
    let movie: Movie
    
    var body: some View {
        ScrollView{
            VStack{
                ZStack{
                    AsyncImage(url: URL(string: movie.imageUrl)){image in
                        image.resizable().scaledToFill()
                    }placeholder: {
                        ProgressView()
                    }
                }
                .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/,minHeight: 300,maxHeight: 300)
                
                VStack(alignment: .leading,spacing: 12){
                    
                    Text(movie.title)
                        .font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
                        .fontWeight(.bold)
                        .fixedSize(horizontal: false, vertical: /*@START_MENU_TOKEN@*/true/*@END_MENU_TOKEN@*/)
                    
                    Button(action:{}){
                        HStack{
                            Image(systemName: "play.fill").foregroundColor(.black)
                            
                            Text("Start watching now")
                                .foregroundColor(.black)
                        }.frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/,maxHeight: 40)
                            .padding()
                            .background(.red)
                    }
                    
                    Text("Released in \(movie.releaseDate)".uppercased())
                        .font(.body)
                    
                    Text(movie.description_)
                        .font(.body)
                        .fixedSize(horizontal: false, vertical: /*@START_MENU_TOKEN@*/true/*@END_MENU_TOKEN@*/)
                }
                .padding(20)
                .background(.black)
                
                
                
                
            }.frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/,maxHeight: .infinity)
        }
    }
}

#Preview {
    DetailScreen(movie: sampleMovie)
}
