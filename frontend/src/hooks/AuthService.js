import api, { handleApiError } from './api'

class AuthService {
  // Connexion utilisateur
  async login(credentials) {
    try {
      const response = await api.post('/auth/signin', credentials)
      
      if (response.data.success) {
        const { user, tokens } = response.data.data
        
        // Stocker le token d'accès
        localStorage.setItem('accessToken', tokens.accessToken.token)
        localStorage.setItem('refreshToken', tokens.refreshToken.token)
        localStorage.setItem('user', JSON.stringify(user))
        
        // Configurer l'header Authorization pour les prochaines requêtes
        api.defaults.headers.common['Authorization'] = `Bearer ${tokens.accessToken.token}`
        
        return {
          success: true,
          user,
          tokens
        }
      }
      
      return {
        success: false,
        error: 'Réponse invalide du serveur'
      }
    } catch (error) {
      const apiError = handleApiError(error)
      return {
        success: false,
        error: apiError.message
      }
    }
  }

  // Inscription utilisateur
  async register(userData) {
    try {
      const response = await api.post('/auth/signup', userData)
      
      if (response.data.success) {
        const { user, tokens } = response.data.data
        
        // Stocker les tokens
        localStorage.setItem('accessToken', tokens.accessToken.token)
        localStorage.setItem('refreshToken', tokens.refreshToken.token)
        localStorage.setItem('user', JSON.stringify(user))
        
        // Configurer l'header Authorization
        api.defaults.headers.common['Authorization'] = `Bearer ${tokens.accessToken.token}`
        
        return {
          success: true,
          user,
          tokens
        }
      }
      
      return {
        success: false,
        error: 'Réponse invalide du serveur'
      }
    } catch (error) {
      const apiError = handleApiError(error)
      return {
        success: false,
        error: apiError.message
      }
    }
  }

  // Déconnexion
  async logout() {
    try {
      const refreshToken = localStorage.getItem('refreshToken')
      
      if (refreshToken) {
        await api.post('/auth/signout', { refreshToken })
      }
    } catch (error) {
      console.error('Erreur lors de la déconnexion:', error)
    } finally {
      // Nettoyer le storage local
      localStorage.removeItem('accessToken')
      localStorage.removeItem('refreshToken')
      localStorage.removeItem('user')
      
      // Supprimer l'header Authorization
      delete api.defaults.headers.common['Authorization']
    }
  }

  // Obtenir l'utilisateur actuel
  async getCurrentUser() {
    try {
      const response = await api.get('/auth/me')
      
      if (response.data.success) {
        return {
          success: true,
          user: response.data.data
        }
      }
      
      return {
        success: false,
        error: 'Utilisateur non trouvé'
      }
    } catch (error) {
      const apiError = handleApiError(error)
      return {
        success: false,
        error: apiError.message
      }
    }
  }

  // Rafraîchir le token d'accès
  async refreshToken() {
    try {
      const refreshToken = localStorage.getItem('refreshToken')
      
      if (!refreshToken) {
        throw new Error('Aucun token de rafraîchissement')
      }
      
      const response = await api.post('/auth/refresh-tokens', { refreshToken })
      
      if (response.data.success) {
        const { tokens } = response.data.data
        
        // Mettre à jour les tokens
        localStorage.setItem('accessToken', tokens.accessToken.token)
        localStorage.setItem('refreshToken', tokens.refreshToken.token)
        
        // Configurer l'header Authorization
        api.defaults.headers.common['Authorization'] = `Bearer ${tokens.accessToken.token}`
        
        return {
          success: true,
          tokens
        }
      }
      
      return {
        success: false,
        error: 'Impossible de rafraîchir le token'
      }
    } catch (error) {
      // Si le refresh échoue, déconnecter l'utilisateur
      this.logout()
      const apiError = handleApiError(error)
      return {
        success: false,
        error: apiError.message
      }
    }
  }

  // Vérifier si l'utilisateur est connecté
  isAuthenticated() {
    const token = localStorage.getItem('accessToken')
    const user = localStorage.getItem('user')
    return !!(token && user)
  }

  // Obtenir l'utilisateur stocké localement
  getStoredUser() {
    try {
      const user = localStorage.getItem('user')
      return user ? JSON.parse(user) : null
    } catch (error) {
      console.error('Erreur lors de la récupération de l\'utilisateur:', error)
      return null
    }
  }

  // Mot de passe oublié
  async forgotPassword(email) {
    try {
      const response = await api.post('/auth/forgot-password', { email })
      
      return {
        success: true,
        message: response.data.data
      }
    } catch (error) {
      const apiError = handleApiError(error)
      return {
        success: false,
        error: apiError.message
      }
    }
  }

  // Réinitialiser le mot de passe
  async resetPassword(token, password) {
    try {
      const response = await api.post(`/auth/reset-password?token=${token}`, { password })
      
      return {
        success: true,
        message: response.data.data
      }
    } catch (error) {
      const apiError = handleApiError(error)
      return {
        success: false,
        error: apiError.message
      }
    }
  }
}

export default new AuthService()